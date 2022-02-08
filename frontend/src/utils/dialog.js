import store from '../store'

let __dialog = {
  dialogInfo: {
    text: "Dialog 메시지를 입력하세요",
    question: false,
    hideDialog: null,
    submitDialog: null,
  },
  showDialog: false,
  dialogCallback: function(){alert("dialog 콜백을 확인해주세요")},
  makeDialog: function(payload) {
    // alert창인지, yes or no question 인지
    if (payload.question != null) {
      this.dialogInfo.question = payload.question
    } else if (payload.question == null) {
      this.dialogInfo.question = false
    }

    // message 확인
    if (payload.text) {
      this.dialogInfo.text = payload.text
    } else {
      this.dialogInfo.text = "Dialog 메시지를 입력하세요"
    }

    // callback이 있는경우
    if (payload.callback != null) {
      this.dialogCallback = payload.callback
    } else {
      this.dialogCallback = null
    }

    // no를 누른경우
    this.dialogInfo.hideDialog = () => {
      this.showDialog = false
    }
    // yes를 누른경우 doalog창을 닫고 callback을 실행
    this.dialogInfo.submitDialog = () => {
      this.showDialog = false
      if (this.dialogCallback) {
        this.dialogCallback()
      }
    }

    if (payload.delay != null) {
      setTimeout(()=>{this.showDialog=true}, payload.delay)
    } else {
      this.showDialog = true
    }
    store.set('dialog/dialogManager', __dialog)
  }
}

export default __dialog

