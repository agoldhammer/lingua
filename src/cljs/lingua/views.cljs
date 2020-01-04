(ns lingua.views
  (:require
   [re-frame.core :as rf]
   [lingua.subs :as subs]
   ))

;; some utility functions for coords

(defn id->elt
  "get DOM elt corresp to id"
  [id]
  (.getElementById js/document id))

(defn id->rect
  "element id -> bounding rectangle"
  [id]
  (.getBoundingClientRect (id->elt id)))

(defn id->center
  "get coordinates of center of elt id"
  [id]
  (let [rect (id->rect id)
        left (.-left rect)
        top (.-top rect)
        xc (+ left (* 0.5 (- (.-right rect) left)))
        yc (+ top (* 0.5 (- (.-bottom rect) top)))]
    [xc yc]))

(defn topleft [id]
  (let [rect (id->rect id)]
    [(.-left rect) (.-top rect)]))

;; views

(defn controls []
  [:div.btn-row
   [:div.src-url
    [:label "Source URL: "]
    [:input.src-url {:type "text"}]]
   [:div.src-lang
    [:label "Source Lang: "]
    [:select
     [:option.src-opt "English"]
     [:option.src-opt "German"]
     [:option.src-opt "Italian"]
     [:option.src-opt "French"]]]
   [:div.target-lang
    [:label "Target Lang: "]
    [:select
     [:option.target-opt "German"]
     [:option.target-opt "Italian"]
     [:option.target-opt "French"]
     [:option.target-opt "English"]]]
   [:div.src-or-target
    [:label "Display: "]
    [:input.src-or-target {:type "button" :value  "source"}]]])

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])
        error-msg @(rf/subscribe [::subs/error])]
    [:div.container
     [controls]
     [:div#from
      [:textarea {:placeholder error-msg}]]
     [:div#to
      [:textarea {:placeholder name}]]
     [:div#usertext
      [:textarea]]
     [:div#usertrans
      [:textarea]]
     (when error-msg
       (let [[errx erry] (id->center "from")]
         [:div.errorbox {:style {:position "absolute"
                                 :left errx
                                 :top erry}}
          error-msg]))
     #_[:div.loader {:style {:position "absolute"
                             :left 384
                             :top 205}}]]))
