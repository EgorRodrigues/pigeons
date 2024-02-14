(ns pigeons.components
  (:require [io.pedestal.http :as http]
            [com.stuartsierra.component :as component]
            [pigeons.diplomat.http-server :as http-server]))

(def ^:private web-config {:env                 :prod
                 ::http/routes        http-server/routes
                 ::http/resource-path "/public"
                 ::http/type          :jetty
                 ::http/host          "0.0.0.0"})

(defrecord WebServer [port]
  component/Lifecycle

  (start [component]
    (assoc component
      ::pedestal
      (http/start (http/create-server
                   (-> web-config
                       (assoc ::http/port (Integer/parseInt (or (System/getenv "PORT") 8080)))
                       (http/default-interceptors))))))

  (stop [component]
    (-> component ::pedestal http/stop)
    component))

(defn new-web-server [port]
  (map->WebServer {:port port}))

(defn system [& options]
  (let [{:keys [port]} options]
    (component/system-map
     :server   (new-web-server port))))
