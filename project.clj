(defproject liberator-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [liberator "0.13"]
                 [cheshire "5.2.0"]
                 [org.clojure/java.jdbc "0.6.0-rc1"]
                 [postgresql "9.3-1102.jdbc41"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler liberator-service.handler/app
         :init liberator-service.handler/init
         :destroy liberator-service.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
