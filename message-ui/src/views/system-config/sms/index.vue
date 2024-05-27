<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="运营商" prop="supplier">
        <el-input
          v-model="queryParams.supplier"
          placeholder="请输入运营商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签名" prop="signature">
        <el-input
          v-model="queryParams.signature"
          placeholder="请输入签名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      :data="smsList"
      :row-key="getRowId"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="accessKeyId"
        align="center"
        prop="accessKeyId"
        v-if="true"
      />
      <el-table-column
        label="accessKeySecret"
        align="center"
        prop="accessKeySecret"
      />
      <el-table-column label="配置 ID" align="center" prop="configId" />
      <el-table-column label="签名" align="center" prop="signature" />
      <el-table-column label="运营商" align="center" prop="supplier" />
      <el-table-column label="模版 ID" align="center" prop="templateId" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改回访工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="550px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="配置 ID" prop="configId">
          <el-input v-model="form.configId" placeholder="请输入配置 ID" />
        </el-form-item>
        <el-form-item label="accessKeyId" prop="accessKeyId">
          <el-input
            v-model="form.accessKeyId"
            placeholder="请输入accessKeyId"
          />
        </el-form-item>
        <el-form-item label="accessKeySecret" prop="accessKeySecret">
          <el-input
            v-model="form.accessKeySecret"
            placeholder="请输入accessKeySecret"
          />
        </el-form-item>
        <el-form-item label="签名" prop="signature">
          <el-input v-model="form.signature" placeholder="请输入签名" />
        </el-form-item>
        <el-form-item label="运营商" prop="supplier">
          <el-input v-model="form.supplier" placeholder="请输入运营商" />
        </el-form-item>
        <el-form-item label="模版 ID" prop="templateId">
          <el-input v-model="form.templateId" placeholder="请输入模版 ID" />
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addSms, deleteSms, updateSms, getSms, listSms } from "@/api/sms";

export default {
  name: "sms",
  data() {
    return {
      getRowId(row) {
        return row.configId;
      },
      smsList: [],
      expands: [],
      multipleSelection: [],
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      addOrUpdate: undefined,
      // 非单个禁用
      single: true,
      open: false,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        accessKeyId: undefined,
        accessKeySecret: undefined,
        configId: undefined,
        signature: undefined,
        supplier: undefined,
        templateId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        configId: [
          { required: true, message: "配置ID不能为空", trigger: "blur" },
        ],
        accessKeyId: [
          { required: true, message: "accessKeyId不能为空", trigger: "blur" },
        ],
        accessKeySecret: [
          {
            required: true,
            message: "accessKeySecret不能为空",
            trigger: "blur",
          },
        ],
        signature: [
          { required: true, message: "签名不能为空", trigger: "blur" },
        ],
        supplier: [
          { required: true, message: "运营商不能为空", trigger: "blur" },
        ],
        templateId: [
          { required: true, message: "模版 ID不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询回访工单列表 */
    getList() {
      this.expands = [];
      this.loading = true;
      listSms(this.queryParams).then((response) => {
        this.smsList = response.data;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {};
      // this.recordBtn = undefined;
      // this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.returnOrderId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.addOrUpdate = 1;
      this.title = "添加邮箱";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();

      const configId = row.id;
      getSms(configId).then((response) => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.addOrUpdate = 2;
        this.title = "修改邮箱";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.addOrUpdate === 2) {
            updateSms(this.form)
              .then((response) => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else if (this.addOrUpdate === 1) {
            addSms(this.form)
              .then((response) => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const configId = row.id;
      this.$modal
        .confirm('是否确认删除sms编号为"' + configId + '"的数据项？')
        .then(() => {
          this.loading = true;
          return deleteSms(configId);
        })
        .then(() => {
          this.loading = false;
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {})
        .finally(() => {
          this.loading = false;
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/config/export/sms",
        {
          ...this.queryParams,
        },
        `email_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
<style lang="scss" scoped>
.el-form-item .el-input {
  width: 100%;
}

.el-form-item .el-select {
  width: 100%;
}

.el-form-item .el-date-picker {
  width: 100%;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand ::v-deep label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-left: 20px;
  margin-right: 0;
  margin-bottom: 0;
  width: 40%;
}

::v-deep {
  .el-drawer__body {
    padding-right: 10px;
  }
}

::v-deep .el-step__title {
  font-size: 14px;
}

.el-upload__tip {
  line-height: 1.2;
}

img {
  height: 200px;
}

video {
  width: 800px;
  height: 400px;
  margin: 0 auto;
  text-align: center;
}

.time-line {
  display: flex;
  margin-bottom: 80px;

  .time-line-item {
    position: relative;
    flex: 1;
    height: 2px;
    background-color: #333;
    text-align: left;

    .circle {
      position: absolute;
      top: -5px;
      // left: 10%;
      width: 10px;
      height: 10px;
      border-radius: 50%;
      background-color: inherit;
      // transform: translateX(-50%);
    }

    .time-line-item-info {
      display: flex;
      flex-direction: column;
      margin-top: 10px;

      .time {
        font-size: 12px;
      }
    }

    &.active {
      color: #46a6ff;
      background-color: #46a6ff;
    }
  }
}
.head {
  display: flex;
  align-items: center;
  padding: 10px;
  .btn {
  }
}
.container {
  padding: 0 10px;
  box-sizing: border-box;
}
</style>
