var url = "/api/admin/management/posts";

$(() => {
  $.ajax({
    url: `${url}`,
    method: "GET",
    success: (data) => {
      table = $("#dataTable").DataTable({
        destroy: true, //테이블 데이터 초기화
        scrollCollapse: true,
        data: data,
        order: [5, "desc"],
        scrollY: "70vh",
        pageLength: 20,
        lengthMenu: [0, 10, 20],
        columns: [
          { data: "id" },
          { data: "userId"},
          { data: "writer" },
          { data: "imgUrl" },
          { data: "likeCount" },
          { data: "uploadDate" },
          { data: "",
            defaultContent:"<button id=btn_deletePost>삭제</button>",
          },
        ],
        columnDefs: [
        {
            targets: 3,
            render: (data) => {
            return `<img src=${data} style="width: 6rem" />`;
            },
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

$("#dataTable > tbody").on("click", "#btn_deletePost", function () {
    if (!confirm("해당 게시글을 삭제하시겠습니까?")) {
        return false;
        } else {
        let id = table.row($(this).parents("tr")).data().id;
        $.ajax({
            url: `${url}/${id}`,
            type: "DELETE",
            success: function () {
            document.location.reload();
            },
        });
    }
});



