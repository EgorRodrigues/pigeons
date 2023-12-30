(ns pigeons.server
  (:gen-class) ; for -main method in uberjar
  (:require [io.pedestal.http :as http]
            [pigeons.service :as service]))

(defonce service-instance nil)

(defn create-server []
  (alter-var-root #'service-instance
                  (constantly (http/create-server
                               (-> service/service
                                   (assoc ::http/port (Integer/parseInt (or (System/getenv "PORT") 8080)))
                                   (http/default-interceptors))))))

(defn start []
  (when-not service-instance
    (create-server))
  (println "Starting server on port" (::http/port service-instance))
  (http/start service-instance))


(defn -main
  [& args]
  (println "\nCreating your server...")
  (start))
