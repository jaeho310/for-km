const __dateUtil =  {
  getFormatDate: (date) => {
    let year = date.getFullYear();
    let month = (1 + date.getMonth());
    month = month >= 10 ? month : '0' + month;
    var day = date.getDate();
    day = day >= 10 ? day : '0' + day;
    return  year + '-' + month + '-' + day;
  },
  timestamp(utcMilliSecond) {
    if (!utcMilliSecond) {
      return null
    }
    let d=new Date(utcMilliSecond)
    return d.getFullYear()+"-"+ this.pad(d.getMonth()+1)+"-"+ this.pad(d.getDate())+" "+ this.pad(d.getHours())+":"+ this.pad(d.getMinutes())+
    ":"+ this.pad(d.getSeconds()) + '.' + this.millipad(d.getMilliseconds())
  },
  pad(n) {
    return n<10 ? "0"+n : n
  },
  millipad(n) {
    if (n < 10) {
      return "00"+n
    } else if (n < 100) {
      return "0"+n
    } else {
      return n
    }
  },

}

export default __dateUtil;