# v1.0.3-1.19 Minor Update
## Others
- Updated to Minecraft version 1.19.4

# v1.0.2-1.19 Major Update
## Others
- Updated the tooltip parameter of the Theme.drawTooltip method to take in a list of `ClientTooltipComponent` instead of `FormattedText` to increase the capabilities of Tooltips
- Added `withComponent` method for adding `ClientTooltipComponent` to tooltips to `Tooltip.Builder`

# v1.0.1 - 1.19 Minor Upate
## Others
- Fixed screen not being closed after hitting esc or e

# v1.0.0 - 1.19.3 Major Update
## Others
- Ported most functionalities to minecraft version 1.19.3
- Changes below are the differences compared for Tau v1.2.0 - 1.16.x
- `Tooltip` no longer allow custom colors for its border and background
- Removed `showDecimals` field from `Slider`
- Added parameter `stepSize` and `decimalPlaces` to `Slider`
- Renamed `Renderable` to `RenderableComponent`
- Added method `drawTooltip` to `Theme`
- Removed tooltip color related methods from `Theme`
