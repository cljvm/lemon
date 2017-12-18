(ns com.lemon.db.demo
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "com/lemon/db/sql/demo.sql")

(hugsql/def-sqlvec-fns "com/lemon/db/sql/demo.sql")