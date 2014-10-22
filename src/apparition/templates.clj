(ns
  ^{:author Oleg}
  apparition.templates
  (:require [hiccup.compiler :refer :all]
            [hiccup.page :refer :all]
            [apparition.util :refer [md->html]]))

(defn boots-theme [theme]  (str "//bootswatch.com/" theme "/bootstrap.min.css"))

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
                  (boots-theme "darkly"))
    (include-js   "//code.jquery.com/jquery-2.1.1.min.js"
                  "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")]))

(defn ^{:menu :home} home[& [params]] ( base
  [:div
    [:div.jumbotron
      [:h1 "Welcome to apparition"]
      [:p "The transient text board"]]
    (md->html "/md/home.md")]
  params))

(defn ^{:menu :about} about[& [params]] (base (md->html "/md/about.md") params))

