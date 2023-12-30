(defproject pigeons "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.6.3"]
                 [io.pedestal/pedestal.jetty "0.6.3"]
                 [com.stuartsierra/component "1.1.0"]
                 [com.stuartsierra/component.repl "0.2.0"]
                 [com.novemberain/monger "3.6.0"]
                 [http-kit/http-kit "2.7.0"]
                 [yogthos/config "1.1.7"]

                 [ch.qos.logback/logback-classic "1.2.10" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.35"]
                 [org.slf4j/jcl-over-slf4j "1.7.35"]
                 [org.slf4j/log4j-over-slf4j "1.7.35"]]

  :profiles {:dev {:source-paths   ["dev"]
                   :resource-paths ["test/resources/"]
                   :plugins        [[com.github.clojure-lsp/lein-clojure-lsp "1.4.4"]]
                   :dependencies   [[io.pedestal/pedestal.service-tools "0.6.3"]
                                    [codestyle/codestyle "0.17.0"]
                                    [org.clojure/tools.namespace "1.4.4"]]
                   :repl-options   {:init-ns user}}

             :uberjar {:aot [pigeons.server]
                       :uberjar-name "pigeons.jar"}}

  :aliases {"diagnostics"     ["clojure-lsp" "diagnostics"]
            "format"          ["clojure-lsp" "format" "--dry"]
            "format-fix"      ["clojure-lsp" "format"]
            "clean-ns"        ["clojure-lsp" "clean-ns" "--dry"]
            "clean-ns-fix"    ["clojure-lsp" "clean-ns"]
            "lint"            ["do" ["diagnostics"]  ["format"] ["clean-ns"]]
            "lint-fix"        ["do" ["format-fix"] ["clean-ns-fix"]]}

  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  :main ^{:skip-aot true} pigeons.server)
