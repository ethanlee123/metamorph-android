package com.verdant.metamorph.ui.orderdetails

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.verdant.metamorph.R
import com.verdant.metamorph.databinding.FragmentOrderDetailsBottomSheetBinding
import com.verdant.metamorph.model.OrderDetailsByIdParams
import com.verdant.metamorph.model.OrderDetailsByIdResponse
import com.verdant.metamorph.ui.orderdetails.repository.OrderDetailsRepository
import com.verdant.metamorph.ui.orderdetails.viewmodel.OrderDetailsViewModel
import com.verdant.metamorph.ui.orderdetails.viewmodel.OrderDetailsViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class OrderDetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentOrderDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val orderDetailsRepository = OrderDetailsRepository()

    private lateinit var orderDetailsViewModel: OrderDetailsViewModel

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

        setupToolbar()

        orderDetailsViewModel.orderDetails.observe(this) {
            setupTextViews(it)
            setupDownloadDocsButton(it.ReferenceDownloadURL, it.ReferenceFileName, it.OrderNo)
        }

        return root
    }

    private fun setupDownloadDocsButton(
        referenceDownloadURL: String?,
        fileName: String?,
        orderNo: String
    ) {
        val downloadDocButton = binding.bReferenceDownloadUrl
        if (referenceDownloadURL.isNullOrBlank()) {
            downloadDocButton.text = resources.getString(R.string.no_docs_available)
            return
        }
        val finalFileName =
            if (fileName.isNullOrBlank()) orderNo
            else fileName

        downloadDocButton.setOnClickListener {
            val request = DownloadManager.Request(Uri.parse(referenceDownloadURL))
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setTitle(finalFileName)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(false)
            val downloadManager =
                requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }
        // Button is disabled by default in xml
        downloadDocButton.isEnabled = true
    }

    private fun setupToolbar() {
        binding.materialToolbar.setNavigationOnClickListener {
            dismiss()
        }
    }

    private fun setupTextViews(data: OrderDetailsByIdResponse) {
//        Order details
        binding.tvOrderNo.text = concatString(R.string.order_no, data.OrderNo)
        binding.tvOrderStatus.text = concatString(R.string.order_status, data.OrderStatus)
        binding.tvClientNo.text = concatString(R.string.client_no, data.ClientNo)

        binding.tvAssignedTranslatorId.text = concatString(R.string.assigned_translator_id, data.AssignedTranslatorID)
        binding.tvDeliveryPlanId.text = concatString(R.string.delivery_plan_id, data.DeliveryPlanID)
        binding.tvDeliveryPlan.text = concatString(R.string.delivery_plan, data.DeliveryPlan)
        binding.tvDeliveryType.text = concatString(R.string.delivery_type, data.DeliveryType)
        binding.tvDeliveryTime.text = concatString(R.string.delivery_time, data.DeliveryTime)
        binding.tvDeliveryLevelName.text = concatString(R.string.delivery_level, data.DeliveryLevelName)
        binding.tvCurrencySymbol.text = concatString(R.string.currency_symbol, data.CurrencySymbol)

        binding.tvOrderDate.text = concatString(R.string.order_date, data.OrderDate)
        binding.tvStartDate.text = concatString(R.string.start_date, data.StartDate)
        binding.tvEndDate.text = concatString(R.string.end_date, data.EndDate)
        binding.tvDeliveryDate.text = concatString(R.string.delivery_date, data.DeliveryDate)

        binding.tvWordCount.text = concatString(R.string.words, data.WordCount)
        binding.tvCountType.text = concatString(R.string.count_type, data.CountType)
        binding.tvCharacterCount.text = concatString(R.string.characters, data.CharacterCount)
        binding.tvPaymentAmount.text = concatString(R.string.payment_amount, data.PaymentAmount)
        binding.tvTranslatorFee.text = concatString(R.string.translator_fee, data.TranslatorFee)
        binding.tvEstimatedPrice.text = concatString(R.string.estimated_price, data.EstimatedPrice)

        binding.tvUnitPrice.text = concatString(R.string.unit_price, data.UnitPrice)
        binding.tvDiscount.text = concatString(R.string.discount, data.Discount)
        binding.tvPriceAfterDiscount.text = concatString(R.string.discounted_price, data.PriceAfterDiscount)
        binding.tvConsumptionTax.text = concatString(R.string.consumption_tax, data.ConsumptionTax)
        binding.tvEvaluationScore.text = concatString(R.string.evaluation_score, data.EvaluationScore)
        binding.tvEvaluationComment.text = concatString(R.string.evaluation_comment, data.EvaluationComment)
        binding.tvCountFavouriteTranslator.text = concatString(R.string.count_favourite_translator, data.CountFavouriteTranslator)
        binding.tvCountBlackTranslator.text = concatString(R.string.count_black_translator, data.CountBlackTranslator)

        binding.tvTranslatorName.text = concatString(R.string.translator_name, data.TranslatorName)
        binding.tvTranslationFieldName.text = concatString(R.string.translation_field_name, data.TranslationFieldName)
        binding.tvTranslatorNo.text = concatString(R.string.translator_no, data.TranslatorNo)
        binding.tvSourceLang.text = concatString(R.string.source_language, data.SourceLanguage)
        binding.tvTargetLang.text = concatString(R.string.target_language, data.TargetLanguage)

        binding.tvAppointedToStaffList.text = concatString(R.string.appointed_to_staff_list, data.AppointedToStaffList)
        binding.tvMessageList.text = concatString(R.string.message_list, data.MessageList)
        binding.tvAvgScore.text = concatString(R.string.avg_score, data.AvgScore)

        binding.tvNumberOfWorks.text = concatString(R.string.number_of_works, data.NumberOfWorks)
        binding.tvCustomerEvaluationPoint.text = concatString(R.string.customer_evaluation_point, data.CustomerEvaluationPoint)
        binding.tvPenaltyPoint.text = concatString(R.string.penalty_point, data.PenaltyPoint)

        binding.tvWebOrderTitle.text = concatString(R.string.web_order_title, data.WebOrderTitle)
        binding.tvSentenceStyle.text = concatString(R.string.sentence_style, data.SentenceStyle)
        binding.tvTranslationMethod.text = concatString(R.string.translation_method, data.TranslationMethod)

//        Comments/menuscript
        binding.tvCommentToTranslator.text =
            concatString(R.string.comment_to_translator, data.CommentToTranslator)
        binding.tvDeliveryComment.text =
            concatString(R.string.delivery_comment, data.DeliveryComment)
        binding.tvMenuScript.text = concatString(R.string.menu_script, data.MenuScript)
    }

    private fun concatString(stringId: Int, secondString: Any): String {
        return "${resources.getString(stringId)}: $secondString"
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