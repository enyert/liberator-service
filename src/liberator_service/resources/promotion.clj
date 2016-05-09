(ns liberator-service.resources.promotion
  (:require [liberator.core
             :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string]]
            [liberator-service.models.db :refer 
             [read-all-promotions
              read-one-promotion
              update-promotion
              create-promotion
              delete-promotion]]))


(defresource promotion-all []
  :service-available? true
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (read-all-promotions)))
  :available-media-types ["application/json"])

(defresource promotion-one [id]
  :service-available? true
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (read-one-promotion id)))
  :available-media-types ["application/json"])

(defresource promotion-create [promotion]
  :service-available? true
  :allowed-methods [:post]
  :post! (fn [_] (create-promotion promotion))
  :handle-created promotion
  :available-media-types ["application/json"])

(defresource promotion-update [id promotion]
  :service-available? true
  :allowed-methods [:put]
  :put! (fn [_] (update-promotion id promotion))
  :handle-created promotion
  :available-media-types ["application/json"])

(defresource promotion-delete [id]
  :service-available? true
  :allowed-methods [:delete]
  :delete! (fn [_] (delete-promotion id))
  :available-media-types ["application/json"])
