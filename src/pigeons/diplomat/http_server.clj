(ns pigeons.diplomat.http-server
  (:require [clojure.pprint :as pp]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [ring.util.response :as ring-resp]))

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
  [{:keys [json-params]}]
  (pp/pprint json-params)
  {:status 200})

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/" :get (conj common-interceptors `home-page)]
              ["/about" :get (conj common-interceptors `about-page)]
              ["/webhook" :get (conj common-interceptors `verify-token)]
              ["/webhook" :post (conj common-interceptors `receive-msg)]})
