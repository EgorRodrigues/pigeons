(ns pigeons.diplomat.http-server
  (:require
   [config.core :refer [env]]
   [monger.collection :as mc]))

(defn verify-token
  [{:keys [query-params] :as request}]
  (if (= (:hub.verify_token query-params) (:token env))
    {:status 200 :body (:hub.challenge query-params)}
    {:status 403}))

(defn receive-msg
  [{:keys [json-params context-deps]}]
  (let [db (-> context-deps :database :database)]
    (mc/insert db "chat-message" {:teste "esse vai"}))
  {:status 200
   :body   "Hello!"})

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

(def routes #{["/greet" :get respond-hello :route-name :greet]
              ["/webhook" :get verify-token :route-name :verify-token]
              ["/webhook" :post receive-msg :route-name :receive-msg]})
