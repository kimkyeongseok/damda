var url = "/api/admin/management/payments";

$(() => {
  $.ajax({
    url: `${url}`,
    method: "GET",
    success: (data) => {
      table = $("#dataTable").DataTable({
        destroy: true, //테이블 데이터 초기화
        scrollCollapse: true,
        data: data,
        order: [4, "date"],
        scrollY: "70vh",
        pageLength: 20,
        lengthMenu: [0, 10, 20],
        columns: [
          { data: "orderId" },
          { data: "date" },
          { data: "buyer" },
          { data: "product" },
          { data: "",
            defaultContent:"<button id=btn_refund>환불</button>",
          },
        ],
      });
    },
    error: () => {
      alert("로그인이 필요합니다.");
      document.location.href = "/admin/page/login";
    },
  });
});

$("#dataTable > tbody").on("click", "#btn_refund", function () {
    if (!confirm("환불 안되는뎅?")) {
        return false;
        } else {
        // let id = table.row($(this).parents("tr")).data().id;
        // $.ajax({
        //     url: `${url}/${id}`,
        //     type: "DELETE",
        //     success: function () {
        //     document.location.reload();
        //     },
        // });
    }
});



