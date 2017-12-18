-- this is demo.sql
-- the demo management

-- :name create-demo-table :! :raw
-- :doc create demo table haha
create table lemon_demo (
  id serial primary key,
  name varchar(20)
)

-- :name insert-demo :! :n
insert into lemon_demo (name)
values (:name)

-- :name update-demo :! :n
update lemon_demo t
set t.name = :name
where t.id = :id

-- :name all-demos :? :*
-- :doc get all demos
select * from lemon_demo order by id

-- :name get-demo :? :1
select * from lemon_demo where id = :id

-- :name find-demo-by-name :? :*
select * from lemon_demo where name = :name

-- :name find-specify-cols-by-ids :? :*
select :i*:cols from lemon_demo where id in (:v*:ids)

-- :snip select-snip
select :i*:cols

-- :snip from-snip
from :i*:tables

-- :snip where-snip
where :snip*:cond

-- :snip cond-snip
-- {conj "and" :cond ["id" "=" 1}
:sql:conj :i:cond.0 :sql:cond.1 :v:cond.2

-- :name snip-query :? :*
:snip:select
:snip:from
--~ (when (:where params) ":snip:where")

-- invoke in clojure is
/*
(snip-query db
{:select (select-snip {:cols ["id" "name"]})
 :from (from-snip {:table ["lemon_demo"]})
 :where (where-snip {:cond [(cond-snip {:conj "" :cond ["id" "=" 1]})
                              (cond-snip {:conj "or" :cond ["id" "=" 2]})]})})
*/