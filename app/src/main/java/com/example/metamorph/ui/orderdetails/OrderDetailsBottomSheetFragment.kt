package com.example.metamorph.ui.orderdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.metamorph.R
import com.example.metamorph.databinding.FragmentOrderDetailsBottomSheetBinding
import com.example.metamorph.model.OrderDetailsByIdParams
import com.example.metamorph.model.OrderDetailsByIdResponse
import com.example.metamorph.ui.orderdetails.viewmodel.OrderDetailsViewModelFactory
import com.example.metamorph.ui.orderdetails.repository.OrderDetailsRepository
import com.example.metamorph.ui.orderdetails.viewmodel.OrderDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class OrderDetailsBottomSheetFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentOrderDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val orderDetailsRepository = OrderDetailsRepository()

    lateinit var orderDetailsViewModel: OrderDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orderDetailsViewModelFactory = OrderDetailsViewModelFactory(orderDetailsRepository)
        orderDetailsViewModel =
            ViewModelProvider(this, orderDetailsViewModelFactory)[OrderDetailsViewModel::class.java]

        getOrderDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentOrderDetailsBottomSheetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        orderDetailsViewModel.orderDetails.observe(this) {
            setupTextViews(it)
        }

        return root
    }

    private fun setupTextViews(data: OrderDetailsByIdResponse) {
//        Order details
        binding.tvOrderId.text = concatString(R.string.order_id, data.OrderId)
        binding.tvOrderNo.text = concatString(R.string.order_no, data.OrderNo)
        binding.tvOrderDate.text = concatString(R.string.order_date, data.OrderDate)
        binding.tvOrderStatus.text = concatString(R.string.order_status, data.OrderStatusName)

//        Translation details
        binding.tvTranslatorName.text = concatString(R.string.translator_name, data.TranslatorName)
        binding.tvTranslationFieldName.text =
            concatString(R.string.translation_field_name, data.TranslationFieldName)
        binding.tvTranslatorNo.text =
            concatString(R.string.translator_no, data.TranslatorNo.toString())
        binding.tvSourceLang.text = concatString(R.string.source_language, data.SourceLanguage)
        binding.tvTargetLang.text = concatString(R.string.target_language, data.TargetLanguage)

//        Comments/menuscript
        binding.tvComments.text = concatString(R.string.comment_to_translator, data.CommentToTranslator)
        binding.tvMenuScript.text = concatString(R.string.menu_script, data.MenuScript)
        binding.tvDeliveryComment.text = concatString(R.string.delivery_comment, data.DeliveryComment)

        binding.tvMenuScript.text = concatString(R.string.menu_script, data.MenuScript)

//        payment details
    }

    private fun concatString(stringId: Int, secondString: String): String{
        return "${resources.getString(stringId)}: ${secondString}"
    }

    private fun getOrderDetails() {
        val orderNumber = arguments?.getString(ARG_ORDER_NO)
        if (orderNumber != null) {
            val orderDetailsByIdParams = OrderDetailsByIdParams(orderNo = orderNumber)
            orderDetailsViewModel.getOrderDetailsById(orderDetailsByIdParams)
        }
    }

    companion object {
        const val TAG = "OrderDetailsBottomSheetFragment"
        const val ARG_ORDER_NO = "order_no"
    }
}