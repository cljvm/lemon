-- this is user.sql
-- the user management

-- :name create-user-table :! :raw
-- :doc create user table haha
create table lemon_user (
    id ....
)

-- :name insert-user :! :n
insert into lemon_user (id, :name)
values (:id, :name)

-- :name update-user :! :n
update lemon_user t
set t.name = :name
where t.id = :id

-- :name all-users :? :*
-- :doc get all users
select * from lemon_user order by id

-- :name get-user :? :1
select * from lemon_user where id = :id

-- :name find-specify-cols-by-ids :? :*
select :i*:cols from lemon_user where id in (:v*:ids)

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
 :from (from-snip {:table ["lemon_user"]})
 :where (where-snip {:cond [(cond-snip {:conj "" :cond ["id" "=" 1]})
                              (cond-snip {:conj "or" :cond ["id" "=" 2]})]})})
*/