// Pathify
import { make } from 'vuex-pathify'

const state = {
  items: [
    {
      title: "home",
      to: '/',
      icon: ''
    },
    {
      title: 'order list',
      to : '/order-list',
      icon: ''
    },
    {
      title: 'login',
      to : '/login',
      icon: ''
    },
  ]
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //
    console.log(dispatch)
  },
}

const getters = {

}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
