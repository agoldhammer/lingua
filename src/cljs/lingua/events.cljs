(ns lingua.events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as rf]
   [lingua.db :as db]
   [cljs-http.client :as http]
   [cljs.core.async :refer [<!]]
   [day8.re-frame.tracing :refer-macros [fn-traced #_defn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))
