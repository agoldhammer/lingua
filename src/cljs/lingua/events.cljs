(ns lingua.events
  #_(:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as rf]
   [lingua.db :as db]
   #_[cljs-http.client :as http]
   #_[cljs.core.async :refer [<! take!]]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]
   [day8.re-frame.tracing :refer-macros [fn-traced #_defn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(def api-url "http://lenny.local:8280/api/")

(defn endpoint [id]
  (str api-url id))

#_(defn json-resp [url]
  (GET (endpoint url)
    :handler #(prn "Response:" %)
    :error-handler #(prn "Error: " %)))

(rf/reg-event-db
 ::good-http-result
 (fn-traced [db [_ result]]
            (prn result)
            (assoc db :show-twirly false)))

(rf/reg-event-db
 ::bad-http-result
 (fn-traced [db [_ error]]
            (prn error)
            (assoc db :show-twirly false)))

(rf/reg-event-fx
 ::handler-with-http
 (fn [{:keys [db]} _]
   {:db   (assoc db :show-twirly true)   ;; causes the twirly-waiting-dialog to show??
    :http-xhrio {:method          :get
                 :uri             (endpoint "de")
                 :timeout         8000                                           ;; optional see API docs
                 :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                 :on-success      [::good-http-result]
                 :on-failure      [::bad-http-result]}}))