(ns com.lemon.db
  (:require [clojure.java.io :as io]
            [hugsql.core :refer [set-adapter!]]
            [hugsql.adapter.clojure-java-jdbc :as adp])
  (:import (com.zaxxer.hikari HikariConfig HikariDataSource)
           (java.util Properties)))

(set-adapter! (adp/hugsql-adapter-clojure-java-jdbc))

(defn- pg-ds
  []
  (let [conf (HikariConfig. "/hikari.properties")
        dsProps (with-open [pgjdbc-stream (-> (io/resource "pgjdbc.properties") (io/input-stream))]
                  (doto (Properties.) (.load pgjdbc-stream)))]
    (.setDataSourceProperties conf dsProps)
    (HikariDataSource. conf)
    ))

(def ^:no-doc ds (pg-ds))

(defn set-ds! [the-ds]
  (alter-var-root #'ds (constantly the-ds)))

(defn get-ds
  []
  (when (nil? ds)
    (set-ds! (pg-ds)))
  ds)

