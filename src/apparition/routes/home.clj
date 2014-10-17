(ns apparition.routes.home
  (:require [compojure.core :refer :all]
            [apparition.layout :as layout]
            [apparition.templates :as pages]))

(defroutes home-routes
  (GET "/" [] (layout/page pages/home))
  (GET "/about" [] (layout/page pages/about)))
