(ns
  ^{:author Oleg}
  apparition.templates
  (:require [hiccup.compiler :refer :all]
            [hiccup.page :refer :all]
            [apparition.util :refer [md->html]]))

(defn base [template & [params]] (html5
  [:head 
    [:title "Welcome to apparition" ]]
  [:body
     [:div.navbar.navbar-inverse;.navbar-fixed-top
       [:div.container
        [:div.navbar-header 
          [:a.navbar-brand {:href "/"} "apparition"]]
        [:div.navbar-collapse.collapse [:ul.nav.navbar-nav
          (for [[el text sub] [[:home "Home" ""] [:about "About"]]]
            (let [sub (if (nil? sub) (subs (str el) 1) sub)]
              [:li {:class (if (el params) "active" nil)}
                [:a {:href (str "/" sub)} text]]))]]]]
    [:div.container template]
    (include-css  "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
                  "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css")
    (include-js   "//code.jquery.com/jquery-2.1.1.min.js"
                  "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")]))

(defn ^{:menu :home} home[& [params]] ( base
  [:div
    [:div.jumbotron
      [:h1 "Welcome to apparition"]
      [:p "Time to start building my site"]
      [:p [:a.btn.btn-primary.btn-lg {:href "http://luminusweb.net"} "Learn more &raquo;"]]]
    (md->html "/md/docs.md")]
  params))

(defn ^{:menu :about} about[& [params]] (base [:p "this is the story of apparition... work in progress"] params))

