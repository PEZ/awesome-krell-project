(ns awesome-krell.core
  (:require [reagent.core :as r]
            [reagent.react-native :as rn]
            [re-frame.core :as rf]
            [awesome-krell.rn-components :as rnc]
            [awesome-krell.events]
            [awesome-krell.subs]))

(defonce splash-img (js/require "../../assets/150.png"))

(def styles
  {:container   {:flex             1
                 :background-color "#fff"
                 :align-items      "center"
                 :justify-content  "center"}
   :title       {:font-weight "bold"
                 :font-size   24
                 :color       "blue"}
   :button      {:font-weight      "bold"
                 :font-size        18
                 :padding          6
                 :background-color "blue"
                 :border-radius    10}
   :button-text {:padding-left  12
                 :padding-right 12
                 :font-weight   "bold"
                 :font-size     18
                 :color         "white"}
   :label       {:font-weight "normal"
                 :font-size   15
                 :color       "blue"}})

(def theme
  {:colors rnc/platform-colors
   :Button {:type         :outline
            :raised       true
            :title-style  {:font-weight :bold
                           :line-height 0}
            :button-style {:padding-left   15
                           :padding-right  15
                           :padding-top    3
                           :padding-bottom 4}}})

(defn root []
  (let [counter (rf/subscribe [:get-counter])]
    (fn []
      [rnc/theme-provider {:theme theme}
       [rn/view {:style (:container styles)}
        [rn/text {:style (:title styles)} "Clicked: " @counter]
        
        [rnc/button {:on-press #(rf/dispatch [:inc-counter])
                     :title "Click me!"}]
        [rn/image {:source splash-img :style {}}]
        [rn/text {:style (:label styles)} "Using: krell+reagent+re-frame"]]])))

(defn ^:export -main [& args]
  (r/as-element [root]))
