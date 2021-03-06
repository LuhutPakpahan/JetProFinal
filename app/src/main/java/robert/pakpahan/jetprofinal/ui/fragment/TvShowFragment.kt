package robert.pakpahan.jetprofinal.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import robert.pakpahan.jetprofinal.R
import robert.pakpahan.jetprofinal.databinding.FragmentTvShowBinding
import robert.pakpahan.jetprofinal.ui.activity.DetailActivity
import robert.pakpahan.jetprofinal.ui.activity.MainActivity
import robert.pakpahan.jetprofinal.ui.adapter.TvShowAdapter
import robert.pakpahan.jetprofinal.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import robert.pakpahan.jetprofinal.ui.viewmodel.TvShowViewModel
import robert.pakpahan.jetprofinal.utils.MarginItemDecoration
import robert.pakpahan.jetprofinal.utils.SortUtils.VOTE_BEST
import robert.pakpahan.jetprofinal.utils.ViewModelFactory
import robert.pakpahan.jetprofinal.vo.Status

class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as MainActivity).setActionBarTitle("TV Shows List")

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShows(VOTE_BEST).observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            showProgressBar(false)
                            tvShowAdapter.submitList(tvShows.data)
                            tvShowAdapter.setOnItemClickCallback(this)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentTvShowBinding.rvTvShows) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)

        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookmark -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark3 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark4 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.pbTvShows.isVisible = state
        fragmentTvShowBinding.rvTvShows.isInvisible = state
    }

}