(ns com.lemon.handler.demo
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [com.lemon.db.demo :as demo]
  		      [com.lemon.db :refer [ds]]
            [clojure.tools.logging :as log]))

(defn- home [req]
  (log/info "Main page")
  {:status 200
   :headers {"content-type" "text/plan"}
   :body "hello"})

(defroutes handler
           (GET "/" req (home req))
           (GET "/demo" req (demo/all-demos {:datasource ds} {}))
           (GET "/add/:name" [name] (str "insert" (demo/insert-demo {:datasource ds} {:name name}) "record"))
           (GET "/find/:name" [name] (demo/find-demo-by-name {:datasource ds} {:name name}))
           (route/not-found "Not Found!!!"))