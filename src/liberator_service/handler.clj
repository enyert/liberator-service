(ns liberator-service.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [liberator-service.routes.product :refer [product-routes]]
            [liberator-service.routes.promotion :refer [promotion-routes]]))

(defn init []
  (println "liberator-service is starting"))

(defn destroy []
  (println "liberator-service is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes product-routes promotion-routes app-routes)
      (handler/site)
      (wrap-base-url)))
