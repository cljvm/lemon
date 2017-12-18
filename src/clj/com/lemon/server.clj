(ns com.lemon.server
  (:gen-class)
  (:require [aleph.http :as http]
            [com.lemon.app :refer [handler]]
            [clojure.tools.logging :refer [info infof] :as log]))

(defn start
  [port]
  ;(log/info (str "starting server port " port " ..."))
  (infof "starting server port %1$d ..." port)
  (http/start-server handler {:port port}))

(defn -main
  [& args]
  (log/info "starting app ...")
  (start 3000))
