(ns lingua.views
  (:require
   [re-frame.core :as re-frame]
   [lingua.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.container
     [:div.tl
      [:h1 "Hello from " @name]]
     ]))
