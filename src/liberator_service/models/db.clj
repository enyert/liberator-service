(ns liberator-service.models.db
  (:require [clojure.java.jdbc :as sql]
            [cheshire.core :refer [parse-string]]))


;; Utility operations

(defn get-environment-var
  "Get the value of an environment variable"
  [name]
  (System/getenv name))

(defn convert-dates 
  "Convert dates from miliseconds to java.sql.Timestamp object"
  [date1 date2]
  (hash-map 
   :starting_date (java.sql.Timestamp. date1)
   :ending_date (java.sql.Timestamp. date2)))

(defn process-promotion 
  "Merge a promotion map with new dates from convert-dates function"
  [promotion]
  (merge promotion
    (convert-dates (:starting_date promotion) (:ending_date promotion))))


;; Database configuration data

(def db {:subprotocol "postgresql"
         :subname (get-environment-var "CLOJUCHIPS_DB_URL")
         :user (get-environment-var "CLOJUCHIPS_DB_USER")
         :password (get-environment-var "CLOJUCHIPS_DB_PASS")})


;; Generic operations

(defn read-all-items 
  "Read all rows from any given entity"
  [table]
  (sql/query db [(str "select * from " table)]))

(defn read-one-item
  "Read an entity with an id provided by the client"
  [table id]
  (first (sql/query db [(str "select * from " table " where id=" id)])))

(defn create-one-item
  "Create an entity using json format"
  [table item]
  (if (= table "promotion")
   (sql/insert! db (keyword table) (process-promotion (parse-string (str item) true)))
   (sql/insert! db (keyword table) (parse-string (str item) true))))

(defn update-item
  "Update an item with an id provided by the client"
  [id table item]
  (if (= table "promotion")
    (sql/update! 
     db 
     (keyword table) 
     (process-promotion (parse-string (str item) true)) 
     [(str "id=" id)])
    (sql/update! db (keyword table) (parse-string (str item) true) [(str "id=" id)])))

(defn delete-item
  "Delete an item with an id provided by the client"
  [table id]
  (sql/delete! db (keyword table) [(str "id=" id)]))


;; Product operations

(defn read-all-products []
  (read-all-items "product"))

(defn read-one-product [id]
  (read-one-item "product" id))

(defn update-product [id item]
  (update-item id "product" item))

(defn create-product [item]
  (create-one-item "product" item))

(defn delete-product [id]
  (delete-item "product" id))


;; Promotion operations

(defn read-all-promotions []
  (read-all-items "promotion"))

(defn read-one-promotion [id]
  (read-one-item "promotion" id))

(defn update-promotion [id item]
  (update-item id "promotion" item))

(defn create-promotion [item]
  (create-one-item "promotion" item))

(defn delete-promotion [id]
  (delete-item "promotion" id))
