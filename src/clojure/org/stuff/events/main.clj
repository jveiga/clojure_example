(ns org.stuff.events.main
  ;(:require [clojure.xml :as xml]
  ;          [clojure.zip :as zip])
  ;(:require [http.async.client :as http])
  (:require [neko.activity :refer [defactivity set-content-view!]]
            [neko.debug :refer [*a]]
            [neko.notify :refer [toast]]
           ; [neko.ui :refer [config make-ui]]
            ;[neko.ui.adapters :refer [ref-adapter]]
            [neko.find-view :refer [find-view find-views]]
            [neko.threading :refer [on-ui]])
  (:import android.widget.TextView))

(def listing (atom ""))

;(defn set-elmt [activity elmt s]
;    (on-ui (config (find-view activity elmt) :text s)))
;
;(defn main-layout [activity]
;  [:linear-layout {:orientation :vertical}
;   [:text-view {:text "Hello from Clojure!"}]])


;(defactivity org.stuff.events.MainActivity
;  :key :main
;  :on-create
;  (fn [this bundle]
;    (on-ui
;      (set-content-view! (*a)
;                         (main-layout (*a))))
;    (on-ui
;      (set-elmt (*a) ::listing @listing))
;    ))

;(on-ui (set-content-view! (*a) [:list-view {:adapter (make-adapter)}]))
(defn notify-from-edit [activity]
  (let [^TextView input (.getText (find-view activity ::user-input))]
    (toast activity
      (if (empty? input)
        "Your input is empty"
        (str "Your input: " input))
      :long)))

(defn layout []
  [:linear-layout {:orientation :vertical
                   :layout-width :fill
                   :layout-height :wrap}
   [:edit-text {:id ::user-input
                :hint "asdasdas"
                :layout-width :fill}]
   [:text-view {:text @listing,
                :id ::listing
                :layout-width :fill}]
   [:button {:text "Touch me"
             :on-click (fn [_] (notify-from-edit (*a)))}]])



(defactivity org.stuff.events.MainActivity
  :key :main
  :on-create
  (fn [^org.stuff.events.MainActivity this bundle]
    (on-ui
      (set-content-view! (*a)
        (layout)
        ))))