(ns apparition.layout
  (:require [clojure.string :as s]
            [ring.util.response :refer [content-type response]]
            [compojure.response :refer [Renderable]]
            [environ.core :refer [env]]))

(deftype RenderableTemplate [func params]
  Renderable
  (render [this request]
    (let [params (assoc params
      :dev (env :dev)
      :servlet-context
        (if-let [context (:servlet-context request)]
         ;; If we're not inside a serlvet environment (for
         ;; example when using mock requests), then
         ;; .getContextPath might not exist
         (try (.getContextPath context)
           (catch IllegalArgumentException _ context))))]

      (content-type (response (func params))
                    "text/html; charset=utf-8"))))

(defmacro page [page-func & [base]]
  `(let [meta#   (meta (var ~page-func))
         menu#   (:menu meta#)
         params# {menu# :selected}]
     (RenderableTemplate. ~page-func params#)))