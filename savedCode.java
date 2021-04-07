	@RequestMapping(value="editInCurrent", method = RequestMethod.GET)
	public ModelAndView userEditShowInCurrent(@ModelAttribute("show") Show show,@RequestParam("episodeNumberToSet") String currentEpisode, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("currentWatchlist");
		if((User)request.getSession().getAttribute("user") != null) {
			User u = (User)request.getSession().getAttribute("user");
			UserCurrentShow foundShow = ucss.getUserCurrentShow(u.getUsername(), show.getShowName());
			foundShow.setCurrentEpisode(Integer.parseInt(currentEpisode));
			foundShow.setCompletionPercentage(Integer.parseInt(currentEpisode));
			ucss.saveUserCurrentShow(foundShow);
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
		}
		return mav;
	}