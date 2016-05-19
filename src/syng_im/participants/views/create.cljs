(ns syng-im.participants.views.create
  (:require [re-frame.core :refer [subscribe dispatch]]
            [syng-im.resources :as res]
            [syng-im.components.react :refer [view list-view list-item]]
            [syng-im.components.toolbar :refer [toolbar]]
            [syng-im.utils.listview :refer [to-datasource]]
            [syng-im.participants.views.contact :refer [participant-contact]]
            [reagent.core :as r]
            [syng-im.participants.styles :as st]))

(defn new-participants-toolbar []
  [toolbar
   {:title     "Add Participants"
    :action    {:image   {:source res/v                     ;; {:uri "icon_search"}
                          :style  st/new-participant-image}
                :handler #(dispatch [:add-new-participants])}}])

(defn new-participants-row
  [row _ _]
  (list-item [participant-contact row]))

(defn new-participants []
  (let [contacts (subscribe [:all-new-contacts])]
    (fn []
      (let [contacts-ds (to-datasource @contacts)]
        [view st/participants-container
         [new-participants-toolbar]
         [list-view {:dataSource contacts-ds
                     :renderRow  new-participants-row
                     :style      st/participants-list}]]))))