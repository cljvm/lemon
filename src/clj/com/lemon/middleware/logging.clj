(ns com.lemon.middleware.logging
  (:require [clojure.tools.logging :refer [info infof] :as log])
  (:import (org.apache.logging.log4j ThreadContext)))


(defn route-info-logging
  [handler]
  (fn [request]
    (log/info (str "matched route info " (:compojure/route request)))
    (handler request)))

(defn wrap-logging
  [handler]
  (fn [request]
    (ThreadContext/put "url" (:uri request))                ; (:query-string request)
    (ThreadContext/put "method" (name (:request-method request)))
    (log/info "========= receive request =========")
    (let [response (handler (assoc request :route-middleware route-info-logging))]
      (log/info "========= send response =========")
      (ThreadContext/clearMap)
      response)))