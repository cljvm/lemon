(ns com.lemon.app
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [com.lemon.middleware.logging :refer [wrap-logging]]
            [compojure.core :refer [defroutes]]
            [com.lemon.handler.demo :as demo]))

(defroutes all-handlers
           demo/handler)

(def lemon-site-defaults
  (-> site-defaults
      (assoc-in [:static :resources] "web")))

(def handler
  (-> all-handlers
      (wrap-json-response {:pretty true})
      (wrap-defaults lemon-site-defaults)
      (wrap-logging)))