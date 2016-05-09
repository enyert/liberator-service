(ns liberator-service.resources.product
  (:require [liberator.core
             :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string]]
            [liberator-service.models.db :refer 
             [read-all-products
              read-one-product
              update-product
              create-product
              delete-product]]))


(defresource product-all []
  :service-available? true
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (read-all-products)))
  :available-media-types ["application/json"])

(defresource product-one [id]
  :service-available? true
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (read-one-product id)))
  :available-media-types ["application/json"])

(defresource product-create [product]
  :service-available? true
  :allowed-methods [:post]
  :post! (fn [_] (create-product product))
  :handle-created product 
  :available-media-types ["application/json"])

(defresource product-update [id product]
  :service-available? true
  :allowed-methods [:put]
  :put! (fn [_] (update-product id product))
  :handle-created product
  :available-media-types ["application/json"])

(defresource product-delete [id]
  :service-available? true
  :allowed-methods [:delete]
  :delete! (fn [_] (delete-product id))
  :available-media-types ["application/json"])
