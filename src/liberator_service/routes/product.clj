(ns liberator-service.routes.product
  (:require [compojure.core :refer :all]
            [liberator-service.resources.product :refer :all]))

(defroutes product-routes
  (context "/api/product" [] (defroutes noparam-routes
    (GET "/" [] (product-all))
    (POST "/" {body :body} (product-create (slurp body)))
      (context "/:id" [id] (defroutes param-routes
        (GET "/" [] (product-one id))
        (PUT "/" {body :body} (product-update id (slurp body)))
        (DELETE "/" [] (product-delete id)))))))

                          
