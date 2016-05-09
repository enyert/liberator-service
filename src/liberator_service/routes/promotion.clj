(ns liberator-service.routes.promotion
  (:require [compojure.core :refer :all]
            [liberator-service.resources.promotion :refer :all]))

(defroutes promotion-routes
  (context "/api/promotion" [] (defroutes noparam-routes
    (GET "/" [] (promotion-all))
    (POST "/" {body :body} (promotion-create (slurp body)))
      (context "/:id" [id] (defroutes param-routes
        (GET "/" [] (promotion-one id))
        (PUT "/" {body :body} (promotion-update id (slurp body)))
        (DELETE "/" [] (promotion-delete id)))))))

                          
