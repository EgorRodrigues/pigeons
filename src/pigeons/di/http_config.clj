(ns pigeons.di.http-config
  (:require [pigeons.diplomat.http-server :as http-server]
            [io.pedestal.http :as http]
            [io.pedestal.http :as server]))

(defn provides [env port]
  (-> {:env                 env
       ::http/routes        http-server/routes
       ::http/resource-path "/public"
       ::http/type          :jetty
       ::http/host          "0.0.0.0"
       ::http/join?         false
       ::http/port          port}
      server/default-interceptors
      server/dev-interceptors))
