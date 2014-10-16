(ns
  ^{:author Oleg}
  apparition.templates
  (:require [hiccup.compiler :refer :all]
            [hiccup.page :refer :all]))

(defn base [template & [params]] (html5
  [:head 
    [:title "Welcome to apparition" ]]
  [:body
    [:div.navbar.navbar-inverse.navbar-fixed-top 
      [:div.container
        [:div.navbar-header 
          [:a.navbar-brand {:href "/"} "apparition"]]
        [:div.navbar-collapse.collapse [:ul.nav.navbar-nav
          (for [[el text sub] [["home" "Home" ""] ["about" "About"] ["some" "Some Dev Work"]]]
            (let [sub (if (nil? sub) el sub)]
              [:li {:class (str el "-selected")} 
                [:a {:href (str "/" sub)} text]]))]]]]
    [:div.container template]
    (include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
      "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css")
    (include-js "//code.jquery.com/jquery-2.1.1.min.js"
      "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")]))

(defn some-page [& [params]] (base [:p "Hello, bro1"] params))

