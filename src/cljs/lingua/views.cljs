(ns lingua.views
  (:require
   [re-frame.core :as rf]
   [lingua.subs :as subs]
   ))

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
  (let [name (rf/subscribe [::subs/name])]
    [:div.container
     [controls]
     [:div.from
      [:textarea]]
     [:div.to
      [:textarea {:placeholder @name}]]
     [:div.usertext
      [:textarea]]
     [:div.usertrans
      [:textarea]]]))
