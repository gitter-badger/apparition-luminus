(ns apparition.routes.home
  (:require [compojure.core :refer :all]
            [apparition.layout :refer [page]]
            [apparition.templates :as template]))

(defroutes home-routes
  (GET "/" [] (page template/home))
  (GET "/about" [] (page template/about)))
