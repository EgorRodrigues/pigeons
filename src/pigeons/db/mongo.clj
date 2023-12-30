(ns pigeons.db.mongo
  (:require
   [monger.collection :as mc]))

(defn insert
  [db collection data]
  (mc/insert db collection data))
