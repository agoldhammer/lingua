(ns lingua.events
  #_(:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as rf]
   [lingua.db :as db]
   #_[cljs-http.client :as http]
   #_[cljs.core.async :refer [<! take!]]
   [ajax.core :refer [GET #_POST]]
   [day8.re-frame.tracing :refer-macros [fn-traced #_defn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(defonce api-url "http://localhost:8280/api/")

(defn endpoint [id]
  (str api-url id))

(defn json-resp [url]
  (GET (endpoint url)
    :handler #(prn "Response:" %)
    :error-handler #(prn "Error: " %)))