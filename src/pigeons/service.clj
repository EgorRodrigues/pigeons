(ns pigeons.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [clojure.pprint :as pp]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(defn verify-token
  [{:keys [query-params] :as request}]
  (if (= (:hub.verify_token query-params) (System/getenv "VERIFY_TOKEN"))
    (do (pp/pprint query-params)
        {:status 200 :body (:hub.challenge query-params)})
    {:status 403}))

(defn receive-msg
  [request]
  (pp/pprint (:json-params request))
  {:status 200})

(def common-interceptors [(body-params/body-params) http/html-body])

;; Tabular routes
(def routes #{["/" :get (conj common-interceptors `home-page)]
              ["/about" :get (conj common-interceptors `about-page)]
              ["/webhook" :get (conj common-interceptors `verify-token)]
              ["/webhook" :post (conj common-interceptors `receive-msg)]})

(def service {:env                 :prod
              ::http/routes        routes
              ::http/resource-path "/public"
              ::http/type          :jetty
              ::http/host          "0.0.0.0"
              ::http/port          8080})
