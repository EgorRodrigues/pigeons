(ns pigeons.di.component
  (:require
   [com.stuartsierra.component :as component]
   [pigeons.di.context-deps :as context-deps]
   [pigeons.di.http-config :as http-config]
   [pigeons.di.mongo :as mongo]
   [pigeons.di.pedestal :as pedestal]))

(defn start-server [environment port]
  (component/system-map
   :database (mongo/provides)
   :service-map (http-config/provides environment port)
   :context-deps (component/using (context-deps/provides) [:database])
   :pedestal (component/using (pedestal/provides) [:service-map :context-deps])))
