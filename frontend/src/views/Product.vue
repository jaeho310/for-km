<template>
  <v-container>
    <v-row class="mx-10">
      <v-col cols="8">
        <v-card class="mx-auto mt-15">
          <v-card-title>
            <div class="text-h5 font-weight-black mb-3">
              상품 목록(원하는 상품을 클릭해주세요)
            </div>
          </v-card-title>
          <v-card-text>
            <v-data-table
                style="border-style: groove; border-width: 1px;"
                :height="tableHeight-150"
                fixed-header
                :items="productListTableBody"
                :headers="productListTableHeader"
                :search="search"
                dense
            >
              <template v-slot:top>
                <v-text-field
                    v-model="search"
                    label="검색"
                    class="mx-4"
                ></v-text-field>
              </template>
              <template
                  v-slot:item="{ item, index }"
              >
                <tr @click="handleClick(item)" :data-category-id="productListTableHeader.id" :data-id="item.id">
                  <td>{{ item.name }}</td>
                  <td>{{ item.price }}</td>
                </tr>
              </template>
            </v-data-table>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="1"></v-col>
      <v-col cols="3">
        <v-card class="mx-auto mt-15">
          <v-card-title>
            <v-col>
              <div class="text-h5 font-weight-black mb-3">
                주문서
              </div>
            </v-col>
            <v-spacer/>
            <v-spacer/>
            <v-col>
              <v-btn class="mb-5" color="primary lighten-1" @click="order">주문하기</v-btn>
            </v-col>
          </v-card-title>
          <v-card-text>
            <v-text-field
                dense
                outlined
                v-model="selectedProductInfo.name"
                label="상품명"
                disabled
                filled
            ></v-text-field>
            <v-text-field
                dense
                outlined
                v-model="selectedProductInfo.price"
                label="가격"
                disabled
                filled
            ></v-text-field>
            <v-text-field
                dense
                outlined
                v-model="selectedProductInfo.count"
                label="수량"
                color="primary"
            ></v-text-field>
            <v-text-field
                dense
                outlined
                v-model="selectedProductInfo.price * selectedProductInfo.count"
                label="총 가격"
                disabled
                filled
            ></v-text-field>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import dialog from "@/utils/dialog";
export default {
  name: "Product",
  data() {
    return {
      isNotify: false,
      search: '',
      selectedProductInfo: {
        id: '',
        name: '',
        price: 0,
        count: 0,
        totalCount: 0
      },
      tableHeight: '0',
      productListTableHeader: [
        {
          text: '상품명',
          value: 'name',
        },
        {
          text: '가격',
          value: 'price',
        },
      ],
      productListTableBody: [
        {
          'name': 'a',
          'price': 100,
          'id': 1,
        },
        {
          'name': 'b',
          'price': 200,
          'id': 2,
        },
        {
          'name': '사과',
          'price': 300,
          'id': 3,
        },
        {
          'name': '사과',
          'price': 300,
          'id': 3,
        },
      ]
    }
  },
  mounted() {
    this.tableHeight = String(window.innerHeight - 300)
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    handleResize() {
      this.tableHeight = String(window.innerHeight - 300)
    },
    handleClick(row) {
      if (!this.isNotify) {
        dialog.makeDialog({text: '수량을 입력하고 주문버튼을 눌러주세요'})
        this.isNotify = true
      }
      this.selectedProductInfo.name = row.name
      this.selectedProductInfo.count = 1
      this.selectedProductInfo.price = row.price
      this.selectedProductInfo.id = row.id
    },
    order() {
      if (this.selectedProductInfo.name === "" || this.selectedProductInfo.count === 0) {
        dialog.makeDialog({text: '올바르지 않은 주문입니다.'})
        return
      }
      console.log(this.selectedProductInfo)
    }
  }

}
</script>

<style scoped>

</style>