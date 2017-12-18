(set-env!
  :project 'lemon
  :version "0.1.0"
  :description "Learn clojure/clojurescript and help my team manage."
  :url ""
  :license {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories #(conj % ["mvnrepository" {:url "http://mvnrepository.com/"}])
  :source-paths #{"src/clj" "src/cljs"}
  :resource-paths #{"resources"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.9.946"]
                  [org.clojure/tools.logging "0.4.0"]
                  [org.clojure/tools.nrepl "0.2.13"]
                  [org.clojure/core.async "0.3.465"]

                  [com.taoensso/sente "1.12.0"]
                  [com.taoensso/encore "2.93.0"]            ;;util
                  ;[com.taoensso/nippy "2.13.0"]             ;;High-performance serialization library for Clojure
                  ;[com.taoensso/timbre "4.10.0"]            ;;A pure Clojure/Script logging library

                  ;[com.fzakaria/slf4j-timbre "0.3.7"]       ;;SLF4J binding for Timbre

                  [org.apache.logging.log4j/log4j-core "2.10.0"]
                  [org.apache.logging.log4j/log4j-slf4j-impl "2.10.0"]

                  [aleph "0.4.4"]

                  [buddy "2.0.0"]

                  [ring/ring-core "1.6.3"]
                  [ring/ring-devel "1.6.3"]
                  [ring/ring-defaults "0.3.1" :exclusions [javax.servlet/servlet-api]]
                  [ring/ring-json "0.4.0" :exclusions [cheshire]]

                  [compojure "1.6.0"]

                  [cheshire "5.8.0"]

                  [org.postgresql/postgresql "42.1.4"]
                  ;[com.impossibl.pgjdbc-ng/pgjdbc-ng "0.7.1"]

                  [com.layerware/hugsql "0.4.8"]

                  [com.zaxxer/HikariCP "2.7.4"]

                  [dk.ative/docjure "1.12.0"]

                  [re-frame "0.10.2"]

                  [adzerk/boot-cljs "2.1.4" :scope "test"]
                  [adzerk/boot-cljs-repl "0.3.3" :scope "test"]
                  [adzerk/boot-reload "0.5.2" :scope "test"]
                  [onetom/boot-lein-generate "0.1.3" :scope "test"]])



(require
  '[boot.lein :as lein]
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[com.lemon.server :as server]
  )

(lein/generate) ; run `boot repl` auto generate project.clj

(task-options!
  pom (merge
        (select-keys (get-env) [:project :version :description :url :license])
        {:developers {"wlx" "wlx@gmail.com"}})
  aot {:namespace #{'com.lemon.server}}
  jar {:main 'com.lemon.server}
  cljs {:ids #{"lemon"}
        :optimizations :simple
        ;:source-map false
        :compiler-options {}}
  )

(deftask run
         "run main"
         [a argv ARG [str] "The argument vector."]
         ;(with-pre-wrap fileset (apply server/-main argv) fileset)
         (with-pass-thru fs (apply server/-main argv))
         ;(apply server/-main argv)
         ;(wait)
         )

(deftask serve
         "run server"
         [a argv ARG [str] "The argument vector."]
         (comp
           (run argv)
           (wait)))

(deftask build
         "Build the package standlone"
         []
         (comp
           (aot)
           (pom)
           (uber)
           (jar)
           (target)))

(deftask dev
         "run a restarable system in the repl"
         []
         (comp
           (watch)
           (cljs :source-map true :optimizations :whitespace)
           (reload)
           (repl
             :server true
             :init-ns 'com.lemon.server)))

(deftask repl-client
         []
         (comp
           (repl :client true)))
