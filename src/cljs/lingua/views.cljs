(ns lingua.views
  (:require
   [re-frame.core :as rf]
   [lingua.subs :as subs]
   ))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]
    [:div.container
     [:div.btn-row
      [:select
       [:option "English"]
       [:option "French"]]
      [:input {:type "button" :value  "hi"}]]
     [:div.tl
      [:h1 "Hello from " @name]]]))
