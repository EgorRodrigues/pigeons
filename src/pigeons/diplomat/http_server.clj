(ns pigeons.diplomat.http-server
  (:require [clojure.pprint :as pp]))

#_(defn verify-token
  [{:keys [query-params] :as request}]
  (if (= (:hub.verify_token query-params) (System/getenv "VERIFY_TOKEN"))
    (do (pp/pprint query-params)
        {:status 200 :body (:hub.challenge query-params)})
    {:status 403}))

#_(defn receive-msg
  [{:keys [json-params]}]
  (pp/pprint json-params)
  {:status 200})

(defn respond-hello [request]
  (pp/pprint request)
  {:status 200 :body "Hello, world!"})

#_(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/greet" :get respond-hello :route-name :greet]})
