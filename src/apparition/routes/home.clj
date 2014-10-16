(ns apparition.routes.home
  (:require [compojure.core :refer :all]
            [apparition.layout :as layout]
            [apparition.util :as util]
            [hiccup.core :refer :all]
            [apparition.templates :as pages]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/some" [] (pages/some-page)))
