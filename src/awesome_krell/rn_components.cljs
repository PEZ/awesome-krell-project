(ns awesome-krell.rn-components
  (:require [react-native :as rn]
            [react-native-elements :as rne]
            [reagent.core :as r]))

(def touchable-opacity (r/adapt-react-class rn/TouchableOpacity))
(def platform (js->clj rn/Platform :keywordize-keys true))

(def button (r/adapt-react-class rne/Button))
(def colors (js->clj rne/colors :keywordize-keys true))
(def theme-provider (r/adapt-react-class rne/ThemeProvider))


(defn platform-specific
  [platforms-map]
  (-> platforms-map
      (clj->js)
      ((:select platform))
      (js->clj :keywordize-keys true)))

(def platform-colors (platform-specific {:default (get-in colors [:platform :android])
                                         :ios (get-in colors [:platform :ios])}))